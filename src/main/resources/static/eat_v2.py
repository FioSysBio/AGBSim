import numpy as np
from cobra.io import load_json_model, save_json_model, load_matlab_model

def eat(reactions, directions, v_in, file_metabolic_model, time, c_in):

    print('-----------------------------')
    try:
        print('metabolitos: ' + str(reactions))
#         reactions = reactions[0]

        print('direções: ' + str(directions))
        directions = np.array(directions)

        c_in = np.array(c_in)
        v_in = np.array(v_in)

        metabolic_model = str(file_metabolic_model)
        time = np.array(time)

        # The Michaelis–Menten kinetic equation proposed for substrate by (Bauer et al., 2017)
        # v_in=-7.56*c_in/(0.01+c_in)

        solver = 'glpk'

        data_dir = '/home/thiago/projetos/fiocruz/acbm-service/src/main/resources/static/'
        file_path = data_dir + metabolic_model
        print(file_path)

        if(file_path[-3:] == 'mat'):
            model = load_matlab_model(file_path)

        if(file_path[-4:] == 'json'):
            model = load_json_model(file_path)

        v_out = np.zeros(len(v_in)+1)

        for i in range(len(v_in)):
            if directions[i] == 0:
                v_out[i] = 0
            else:
                reac = model.reactions.get_by_id(reactions[i])
                if directions[i] == 1:
                    reac.lower_bound = v_in[i]
                elif directions[i] == -1:
                    reac.upper_bound = v_in[i]

        s = model.optimize()
        print(s.fluxes)

        for j in range(len(v_in)):
            if directions[j] != 0:
                v_out[j] = s.fluxes[model.reactions.get_by_id(reactions[j]).id]
                print(v_out[j])

        miu = s.objective_value
        v_out[len(v_out)-1] = miu
        print(v_out)

        return v_out

    except Exception as e:
        print(e)

result = eat(reactions, directions, v_in, file_metabolic_model, time, c_in)
print('saída: ' + str(result))